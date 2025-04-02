import { Component, OnInit } from '@angular/core';
import { MenuService } from '../../../service/menu.service';  
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-menu-admin',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
  ],
  templateUrl: './menu-admin.component.html',
})
export class MenuAdminComponent implements OnInit {
  menus: any[] = [];
  showAddForm = false;
  showEditForm = false;

  newMenu: any = {
    name: '',
    description: '',
    menuDate: '',
    active: true,
    quantity: 1
  };

  editMenu: any = null;

  constructor(private menuService: MenuService) {}

  ngOnInit() {
    this.loadMenus();
  }

  loadMenus() {
    this.menuService.getAllMenus().subscribe((res) => {
      this.menus = res.content;
    });
  }

  createMenu() {
    const menuToCreate = {
      ...this.newMenu,
      menuDate: new Date(this.newMenu.menuDate).toISOString()
    };

    this.menuService.createMenu(menuToCreate).subscribe(() => {
      this.loadMenus();
      this.showAddForm = false;
      this.resetNewMenu();
    });
  }

  openEditModal(menu: any) {
    this.editMenu = {
      ...menu,
      menuDate: menu.menuDate?.slice(0, 16) // Format date for input
    };
    this.showEditForm = true;
  }

  updateMenu() {
    const menuToUpdate = {
      ...this.editMenu,
      menuDate: new Date(this.editMenu.menuDate).toISOString()
    };

    this.menuService.updateMenu(menuToUpdate).subscribe(() => {
      this.loadMenus();
      this.showEditForm = false;
      this.editMenu = null;
    });
  }

  deleteMenu(name: string) {
    if (confirm(`Delete menu "${name}"?`)) {
      this.menuService.deleteMenu(name).subscribe(() => this.loadMenus());
    }
  }

  resetNewMenu() {
    this.newMenu = {
      name: '',
      description: '',
      menuDate: '',
      active: true,
      quantity: 1
    };
  }
}
