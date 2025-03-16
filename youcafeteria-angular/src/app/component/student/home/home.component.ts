import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { HeroComponent } from '../hero/hero.component';
import { DishOfDayComponent } from '../dish-of-day/dish-of-day.component';
import { AboutComponent } from '../about/about.component';
import { TeamComponent } from '../team/team.component';
import { FooterComponent } from '../footer/footer.component';
import { ServicesComponent } from '../services/services.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    HeaderComponent,
    HeroComponent,
    DishOfDayComponent,
    AboutComponent,
    TeamComponent,
    FooterComponent,
    ServicesComponent

  ],
  templateUrl: './home.component.html'
})
export class HomeComponent {

}

