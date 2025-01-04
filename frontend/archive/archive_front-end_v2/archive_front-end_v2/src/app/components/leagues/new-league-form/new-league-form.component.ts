import { CommonModule } from '@angular/common';
import { HttpResponse } from '@angular/common/http';
import { Component, Input, OnDestroy } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { IFormComponent } from '../../../model/form.interface';
import { APIS, CreateLeagueResponse, LeagueBody, LeagueControllerService } from '../../../swagger';

@Component({
  selector: 'app-new-league-form',
  standalone: true,
  providers: [LeagueControllerService],
  imports: [FormsModule, CommonModule, MatDialogModule, MatInputModule, MatFormFieldModule, MatSelectModule],
  templateUrl: './new-league-form.component.html'
})
export class NewLeagueFormComponent implements IFormComponent, OnDestroy {

  private createLeagueSubscription: Subscription;

  constructor(private dialogRef: MatDialogRef<NewLeagueFormComponent>,
    private router: Router,
    private toastService: ToastrService,
    private leagueService: LeagueControllerService
  ) { }

  ngOnDestroy(): void {
    this.createLeagueSubscription?.unsubscribe();
  }

  @Input() title: string;

  onSubmit(form: NgForm): void {
    let leagueBody: LeagueBody = {
      file: new Blob(),
      createLeagueRequest: {
        name: 'Test',
        showId: 1,
        userId: 1,
      }
    };

    this.createLeagueSubscription = this.leagueService.createLeague(leagueBody, "response").subscribe({
      next: (response: HttpResponse<CreateLeagueResponse>) => {
        if (response.status === 201) {
          this.router.navigate(["league", response.body?.leagueId]);
          this.dialogRef.close(form);
        } else {
          this.toastService.error("Unable to Create League. Please check Dev Console.");
        }
      },
      error: (error: Error) => {
        this.toastService.error("Unable to Login. An unknown error occurred.");
      }
    });

  }


}
