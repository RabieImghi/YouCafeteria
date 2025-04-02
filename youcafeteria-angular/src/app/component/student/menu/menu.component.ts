import { Component, OnInit } from '@angular/core';
import { MenuService } from '../../../service/menu.service'; // Adjust path
import { HeaderComponent } from '../header/header.component';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [
    HeaderComponent,
    CommonModule,
    RouterModule
  ],
  templateUrl: './menu.component.html'
})
export class MenuComponent implements OnInit {
  menus: any[] = [];

  constructor(private menuService: MenuService) {}

  ngOnInit() {
    this.menuService.getAllMenus().subscribe(res => {
      this.menus = res.content;
    });
  }
}
