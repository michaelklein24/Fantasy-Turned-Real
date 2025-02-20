import { Routes } from '@angular/router';
import { DashboardPageComponent } from './pages/dashboard/dashboard-page.component';
import { LeagueHomePageComponent } from './features/leagues/pages/league-home-page/league-home-page.component';
import { LeaguePageComponent } from './features/leagues/pages/league-page/league-page.component';
import { LeagueScorePageComponent } from './features/leagues/pages/league-score-page/league-score-page.component';
import { LeagueSettingsPageComponent } from './features/leagues/pages/league-settings-page/league-settings-page.component';
import { LeagueSurveyPageComponent } from './features/leagues/pages/league-surveys-page/league-survey-page.component';
import { LeaguesPageComponent } from './features/leagues/pages/leagues-page/leagues-page.component';
import { LoginUserFormComponent } from './forms/login-user-form/login-user-form.component';
import { RegisterUserFormComponent } from './forms/register-user-form/register-user-form.component';
import { HomePageComponent } from './pages/home/home-page.component';
import { NotificationsPageComponent } from './features/notifications/pages/notifications-page/notifications-page.component';
import { ShowPageComponent } from './pages/show/show-page.component';

export const routes: Routes = [
    { path: "register", component: RegisterUserFormComponent },
    { path: "login", component: LoginUserFormComponent },
    { path: "shows", component: ShowPageComponent },
    { path: "dashboard", component: DashboardPageComponent, children: [
        { path: "", component: HomePageComponent },
        { path: "notification", component: NotificationsPageComponent },
        { path: "league", component: LeaguesPageComponent },
        { path: "league/:leagueId", component: LeaguePageComponent, children: [
            { path: "home", component: LeagueHomePageComponent },
            { path: "score", component: LeagueScorePageComponent },
            { path: "survey", component: LeagueSurveyPageComponent },
            { path: "settings", component: LeagueSettingsPageComponent },
        ]}
    ]}
];
