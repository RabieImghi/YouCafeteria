import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StateService } from '../../../service/state.service';

@Component({
  selector: 'app-header',
  standalone: true,
    imports: [
      CommonModule
    ],
  templateUrl: './header.component.html'
})
export class HeaderComponent {
  menuProfile  = false;

  constructor(private stateService: StateService) { }


  logout() {
    this.stateService.logout();
  }

}
