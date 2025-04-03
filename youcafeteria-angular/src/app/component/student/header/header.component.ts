import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { StateService } from '../../../service/state.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule // Import RouterModule for routing
  ],
  templateUrl: './header.component.html'
})
export class HeaderComponent {
  menuProfile = false;

  constructor(private stateService: StateService, private router: Router) {}

  logout() {
    this.stateService.logout();
    this.router.navigate(['/login']); // Redirect to login after logout
  }
}
