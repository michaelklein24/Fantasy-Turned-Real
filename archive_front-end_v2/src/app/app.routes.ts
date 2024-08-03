import { Routes } from '@angular/router';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { LeaguesComponent } from './components/leagues/leagues.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { LeagueComponent } from './components/leagues/league/league.component';
import { ScoreboardComponent } from './components/leagues/league/scoreboard/scoreboard.component';
import { SurveysComponent } from './components/leagues/league/surveys/surveys.component';
import { ChatComponent } from './components/leagues/league/chat/chat.component';
import { ManageLeagueComponent } from './components/leagues/league/manage-league/manage-league.component';
import { SurveyComponent } from './components/leagues/league/surveys/survey/survey.component';

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'league', component: LeaguesComponent },
    {
        path: 'league/:leagueId', component: LeagueComponent,
        children: [
            { path: 'scoreboard', component: ScoreboardComponent },
            { path: 'survey', component: SurveysComponent },
            { path: 'chat', component: ChatComponent },
            { path: 'manage', component: ManageLeagueComponent },
            { path: 'survey/:surveyId', component: SurveyComponent }
        ]
    },
    { path: '', component: LandingPageComponent }
];
