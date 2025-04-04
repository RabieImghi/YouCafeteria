import { CanActivateFn, Router } from '@angular/router';
import { inject } from "@angular/core";

export const isNotAuthenticatedGuardGuard: CanActivateFn = (route, state) => {
    const router = inject(Router);
    const token = localStorage.getItem('auth_access_token');

    if (token) {
        router.navigate(['']);
        return false; 
    }

    return true; 
};
