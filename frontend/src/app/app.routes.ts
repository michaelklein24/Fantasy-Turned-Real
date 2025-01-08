import { Routes } from '@angular/router';
import { RegisterUserFormComponent } from './forms/register-user-form/register-user-form.component';
import { LoginUserFormComponent } from './forms/login-user-form/login-user-form.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { LeaguesComponent } from './pages/leagues/leagues.component';
import { HomeComponent } from './pages/home/home.component';
import { LeagueComponent } from './pages/league/league.component';
import { LeagueHomeComponent } from './pages/league/league-home/league-home.component';
import { LeagueScoreComponent } from './pages/league/league-score/league-score.component';
import { LeagueSurveyComponent } from './pages/league/league-survey/league-survey.component';
import { LeagueSettingsComponent } from './pages/league/league-settings/league-settings.component';

export const routes: Routes = [
    { path: "register", component: RegisterUserFormComponent},
    { path: "login", component: LoginUserFormComponent},
    { path: "dashboard", component: DashboardComponent, children: [
        { path: "", component: HomeComponent },
        { path: "league", component: LeaguesComponent },
        { path: "league/:id", component: LeagueComponent, children: [
            { path: "home", component: LeagueHomeComponent },
            { path: "score", component: LeagueScoreComponent },
            { path: "survey", component: LeagueSurveyComponent },
            { path: "settings", component: LeagueSettingsComponent },
        ]}
    ]}
];
