import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-menu-detail',
  standalone: true,
  imports: [
    HeaderComponent
  ],
  templateUrl: './menu-detail.component.html'
})
export class MenuDetailComponent {

}
