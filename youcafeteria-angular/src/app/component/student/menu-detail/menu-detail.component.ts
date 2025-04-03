import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { Router, RouterLink } from '@angular/router';
import { PlatService } from '../../../service/plat.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-menu-detail',
  standalone: true,
  imports: [
    HeaderComponent,
    CommonModule,
    RouterLink
  ],
  templateUrl: './menu-detail.component.html'
})
export class MenuDetailComponent {
  menu: any = null;
  plats: any[] = [];
  principalDishes: any[] = [];
  dessertDishes: any[] = [];
  starterDishes: any[] = [];

  constructor(private router: Router, private platService: PlatService) {}

  ngOnInit() {
    this.menu = history.state.menu;
    if (!this.menu) {
      this.router.navigate(['/menu']);
      return;
    }

    this.loadDishesForMenu(this.menu.name);
  }

  loadDishesForMenu(menuName: string) {
    this.platService.getDishesByMenu(menuName).subscribe((res: any) => {
      this.plats = res.content;
      this.plats.forEach((plat: any) => {
        if (plat.dishType === 'PRINCIPAL') {
          this.principalDishes.push(plat);
        } else if (plat.dishType === 'DESERT') {
          this.dessertDishes.push(plat);
        } else if (plat.dishType === 'SALAD') {
          this.starterDishes.push(plat);
        }
      });
    });
  }
}
