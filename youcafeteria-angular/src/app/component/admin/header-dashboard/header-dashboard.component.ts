import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { StateService } from '../../../service/state.service';

@Component({
  selector: 'app-header-dashboard',
  standalone: true,
  imports: [
    RouterLink,
    CommonModule,
  ],
  templateUrl: './header-dashboard.component.html'
})
export class HeaderDashboardComponent {
  menuProfile = false;

    constructor(private stateService: StateService, private router: Router) {}
  
    logout() {
      this.stateService.logout();
      this.router.navigate(['login']); // Redirect to login after logout
    }
}
