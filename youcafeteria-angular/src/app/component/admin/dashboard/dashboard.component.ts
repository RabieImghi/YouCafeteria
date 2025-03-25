import { Component } from '@angular/core';
import { StatistiqueComponent } from '../statistique/statistique.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    StatistiqueComponent
  ],
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent {

}
