import { Routes } from '@angular/router';
import { RegisterUserFormComponent } from './forms/register-user-form/register-user-form.component';
import { LoginUserFormComponent } from './forms/login-user-form/login-user-form.component';

export const routes: Routes = [
    { path: "register", component: RegisterUserFormComponent},
    { path: "login", component: LoginUserFormComponent}
];
