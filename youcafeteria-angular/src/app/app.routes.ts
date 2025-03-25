import { Routes } from '@angular/router';
import { LoginComponent } from './component/auth/login/login.component';
import { HomeComponent } from './component/student/home/home.component';
import { MenuComponent } from './component/student/menu/menu.component';
import { MenuDetailComponent } from './component/student/menu-detail/menu-detail.component';
import { ProfileComponent } from './component/student/profile/profile.component';
import { MainComponent } from './component/admin/main/main.component';
import { ReservationComponent } from './component/student/reservation/reservation.component';
import { ContactComponent } from './component/student/contact/contact.component';
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
    },
    {
        path: 'menu',
        component: MenuComponent,
        canActivate: [isAuthenticatedGuardGuard]
    },
    {
        path: 'menu/detail',
        component: MenuDetailComponent,
        canActivate: [isAuthenticatedGuardGuard]
    },
    {
        path: 'reservation',
        component: ReservationComponent,
        canActivate: [isAuthenticatedGuardGuard]
    },
    {
        path: 'contact',
        component: ContactComponent,
        canActivate: [isAuthenticatedGuardGuard]
    },
    {
        path: 'profile',
        component: ProfileComponent,
        canActivate: [isAuthenticatedGuardGuard]
    },
    {
        path: 'dashboard',
        component: MainComponent,
        canActivate: [isAuthenticatedGuardGuard]
    }
];
