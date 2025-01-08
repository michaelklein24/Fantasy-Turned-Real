import { Routes } from '@angular/router';
import { RegisterUserFormComponent } from './forms/register-user-form/register-user-form.component';
import { LoginUserFormComponent } from './forms/login-user-form/login-user-form.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { LeaguesComponent } from './pages/leagues/leagues.component';
import { HomeComponent } from './pages/home/home.component';
import { LeagueComponent } from './pages/league/league.component';

export const routes: Routes = [
    { path: "register", component: RegisterUserFormComponent},
    { path: "login", component: LoginUserFormComponent},
    { path: "dashboard", component: DashboardComponent, children: [
        { path: "", component: HomeComponent },
        { path: "leagues", component: LeaguesComponent },
        { path: "leagues/:id", component: LeagueComponent }
    ]}
];
