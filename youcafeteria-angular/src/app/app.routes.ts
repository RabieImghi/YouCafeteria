import { Routes } from '@angular/router';
import { LoginComponent } from './component/auth/login/login.component';
import { HomeComponent } from './component/student/home/home.component';
import { isAuthenticatedGuardGuard } from './guard/is-authenticated-guard.guard';
import { isNotAuthenticatedGuardGuard } from './guard/is-not-authenticated-guard.guard';

export const routes: Routes = [
    {
        path: 'auth/login',
        component: LoginComponent,
        canActivate: [isNotAuthenticatedGuardGuard]
    },
    {
        path: '',
        component: HomeComponent,
        canActivate: [isAuthenticatedGuardGuard]
    }
];
