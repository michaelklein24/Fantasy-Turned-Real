import { Component, OnDestroy, OnInit } from '@angular/core';
import { DropdownComponent, DropdownMenuItem } from '../../shared/components/dropdown/dropdown.component';
import { Contestant, ContestantSocial, Season, Show } from '../../../libs/generated/typescript-angular';
import { ContestantService } from '../../features/shows/services/contestant.service';
import { CommonModule } from '@angular/common';
import { ImageService } from '../../core/services/image.service';
import { ShowService } from '../../features/shows/services/show.service';
import { ConfigService } from '../../core/services/config.service';
import { CapitalizeFirstPipe } from "../../shared/pipes/capitalize-first.pipe";
import { ReplaceCharPipe } from '../../shared/pipes/replace-char.pipe';

@Component({
  selector: 'app-show-page',
  standalone: true,
  imports: [
    DropdownComponent,
    CommonModule,
    CapitalizeFirstPipe,
    ReplaceCharPipe
],
  templateUrl: './show-page.component.html',
  styleUrl: './show-page.component.css'
})
export class ShowPageComponent implements OnInit, OnDestroy {

  selectedShow: Show = Show.Survivor;

  selectedSeasonSequence: number = 47;
  contestants: Contestant[] = []
  seasons: Season[] = []
  shows: Show[] = []

  // TODO: Make this configurable on servers
  currentSeasons = [
    { show: Show.Survivor, season: 47 },
    { show: Show.Traitors, season: 3 },
    { show: Show.RuPaulsDragRace, season: 17 }
  ]

  constructor(
    private contestantService: ContestantService,
    private imageService: ImageService,
    private showService: ShowService,
    private configService: ConfigService
  ) {}

  ngOnInit(): void {
    this.loadContestants();
    this.loadSeasons();
    this.loadShows();
  }

  ngOnDestroy(): void {

  }

  private loadContestants(): void {
    this.contestantService.getContestantsForSeason(this.selectedShow, this.selectedSeasonSequence).subscribe((contestants: Contestant[]) => {
      this.contestants = contestants;
    });
  }

  private loadSeasons(): void {
    this.showService.getSeasonsForShow(this.selectedShow).subscribe((seasons: Season[]) => {
      this.seasons = seasons;
    })
  }

  private loadShows(): void {
    this.showService.getShows().subscribe((shows: Show[]) => {
      this.shows = shows;
    })
  }

  handleSeasonSelection(selectedItem: DropdownMenuItem): void {
    const seasonSequence = Number(selectedItem.value);
    console.log(seasonSequence)
    if (seasonSequence !== this.selectedSeasonSequence) {
      this.selectedSeasonSequence = seasonSequence;
      this.loadContestants();
    }
  }

  getSocialUrl(social: ContestantSocial): string {
    switch (social.platform) {
      case 'INSTAGRAM':
        return `https://www.instagram.com/${social.handle}`;
      case 'X':
        return `https://twitter.com/${social.handle}`;
      default:
        return '#';
    }
  }

  getUrlForSeasonTitleImage(show: Show) {
    const folderPath = `show:${show.toLowerCase()}`;
    const fileName = `title_image.jpg`;
    const imageUrl = this.imageService.getImageUrl(folderPath, fileName);
    return imageUrl;
  }

  getUrlForContestantImage(show: Show, seasonSequence: number, firstName: string, lastName: string): string {
    const folderPath = `show:${show.toLowerCase()}:season:${seasonSequence}`;
    const fileName = `${firstName.toLowerCase()}_${lastName.toLowerCase()}.webp`;
    const imageUrl = this.imageService.getImageUrl(folderPath, fileName);
    return imageUrl;
  }

  getSeasonSequenceOptions(): DropdownMenuItem[] {
    return this.seasons.map(season => {
      const option: DropdownMenuItem = {
        label: String(`Season ${season.sequence!}`),
        value: String(season.sequence!)
      }
      return option;
    })
  }

  handleShowSelection(show: Show): void {
    for (const currentSeason of this.currentSeasons) {
      if (show === currentSeason.show) {
        this.selectedShow = show;
        this.selectedSeasonSequence = currentSeason.season;
        this.loadContestants();
        this.loadSeasons();
      }
    }

  }
}
