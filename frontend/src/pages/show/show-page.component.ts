import { Component, OnDestroy, OnInit } from '@angular/core';
import { DropdownComponent, DropdownMenuItem } from '../../shared/components/dropdown/dropdown.component';
import { CommonModule } from '@angular/common';
import { CapitalizeFirstPipe } from '../../shared/pipes/capitalize-first.pipe';
import { ReplaceCharPipe } from '../../shared/pipes/replace-char.pipe';
import { Contestant, ContestantSocial, Season, Show } from '../../libs/generated/typescript-angular';
import { ImageService } from '../../services/image.service';
import { ContestantService } from '../../features/shows/services/contestant.service';
import { ShowService } from '../../features/shows/services/show.service';
import { ConfigService } from '../../services/config.service';

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

  selectedShow: string = 'SURVIVOR';

  selectedSeasonSequence: number = 47;
  contestants: Contestant[] = []
  seasons: Season[] = []
  shows: Show[] = []

  // TODO: Make this configurable on servers
  currentSeasons = [
    { show: 'SURVIVOR', season: 47 },
    { show: 'TRAITORS', season: 3 },
    { show: 'RU_PAULS_DRAG_RACE', season: 17 }
  ]

  constructor(
    private contestantService: ContestantService,
    private imageService: ImageService,
    private showService: ShowService,
    private configService: ConfigService
  ) {}

  ngOnInit(): void {
    this.loadContestants();
    this.loadShows();
  }

  ngOnDestroy(): void {

  }

  private loadContestants(): void {
    this.contestantService.getContestantsForSeason(this.selectedShow, this.selectedSeasonSequence).subscribe((contestants: Contestant[]) => {
      this.contestants = contestants;
    });
  }

  private loadShows(): void {
    this.showService.getShows().subscribe((shows: Show[]) => {
      this.shows = shows; 
      this.selectedSeasonSequence && shows[0].seasons?.map((season: Season) => season.sequence)
      this.selectedShow && shows[0].name;
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

  getUrlForSeasonTitleImage(show: string) {
    const folderPath = `show:${show}`.toLowerCase();
    const fileName = `title_image.jpg`;
    const imageUrl = this.imageService.getImageUrl(folderPath, fileName);
    return imageUrl;
  }

  getUrlForContestantImage(show: string, seasonSequence: number, firstName: string, lastName: string): string {
    const folderPath = `show:${show}:season:${seasonSequence}`;
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
        this.selectedShow = show.name!;
        this.selectedSeasonSequence = currentSeason.season;
        this.loadContestants();
      }
    }

  }
}
