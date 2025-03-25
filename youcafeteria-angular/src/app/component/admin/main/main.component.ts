import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { HeaderDashboardComponent } from '../header-dashboard/header-dashboard.component';
import { DashboardComponent } from '../dashboard/dashboard.component';

@Component({
  selector: 'app-main',
  standalone: true,
  imports: [
    SidebarComponent,
    HeaderDashboardComponent,
    DashboardComponent
  ],
  templateUrl: './main.component.html'
})
export class MainComponent {

}
