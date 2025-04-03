import { Component, OnInit } from '@angular/core';
import { PlatService } from '../../../service/plat.service';
import { MenuService } from '../../../service/menu.service';
import { StockService } from '../../../service/stock.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-plat',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './plat.component.html'
})
export class PlatComponent implements OnInit {
  plats: any[] = [];
  menus: any[] = [];
  stocks: any[] = [];
  stockInputs: { stockName: string, quantity: number }[] = [];
  showAddForm = false;
  showEditForm = false;

  newPlat = {
    name: '',
    description: '',
    image: '',
    available: true,
    quantity: 1,
    dishType: 'PRINCIPAL',
    menuName: '',
    stockNames: [] as string[]
  };

  editPlat: any = null;
  stockMap: { [key: string]: number } = {};

  constructor(
    private platService: PlatService,
    private menuService: MenuService,
    private stockService: StockService
  ) {}

  ngOnInit() {
    this.loadPlats();
    this.loadMenus();
    this.loadStocks();
  }

  loadPlats() {
    this.platService.getAllDishes().subscribe(res => {
      this.plats = res.content;
    });
  }

  updateStockNames() {
    this.newPlat.stockNames = this.stockInputs
      .filter(s => s.stockName && s.quantity > 0)
      .map(s => `${s.stockName}/${s.quantity}`);
  }

  addStockInput() {
    this.stockInputs.push({ stockName: '', quantity: 1 });
    this.updateStockNames();
  }
  
  removeStockInput(index: number) {
    this.stockInputs.splice(index, 1);
    this.updateStockNames();
  }
  
  loadMenus() {
    this.menuService.getAllMenus().subscribe(res => {
      this.menus = res.content;
    });
  }

  loadStocks() {
    this.stockService.getAllStocks().subscribe((res : any) => {
      this.stocks = res.content;
    });
  }

  handleStockChange(name: string, value: string) {
    const quantity = parseInt(value, 10);
    if (!isNaN(quantity) && quantity > 0) {
      this.stockMap[name] = quantity;
    } else {
      delete this.stockMap[name];
    }
    this.newPlat.stockNames = Object.entries(this.stockMap).map(([k, v]) => `${k}/${v}`);
  }

  createPlat() {
    this.platService.createDish(this.newPlat).subscribe(() => {
      this.loadPlats();
      this.showAddForm = false;
      this.resetNewPlat();
    });
  }

  openEditModal(plat: any) {
    this.editPlat = {
      ...plat,
      menuName: plat.menu?.name,
      stockNames: plat.stocks?.map((s: any) => `${s.stock.name}/${s.quantity}`)
    };
    console.log(this.editPlat);
    this.showEditForm = true;
  }

  updatePlat() {
    const updatedPlat = {
      name: this.editPlat.name,
      description: this.editPlat.description,
      image: this.editPlat.image,
      available: this.editPlat.available,
      quantity: this.editPlat.quantity,
      dishType: this.editPlat.dishType,
      menuName: this.editPlat.menuName,
      stockNames: this.editPlat.stockNames || [],
    };
  
    this.platService.updateDish(updatedPlat).subscribe(() => {
      alert('Dish updated successfully!');
      this.loadPlats();
      this.showEditForm = false;
      this.editPlat = null;
    }, err => {
      console.error(err);
      alert('Failed to update dish.');
    });
  }
  
  deletePlat(name: string) {
    if (confirm(`Delete dish "${name}"?`)) {
      this.platService.deleteDish(name).subscribe(() => this.loadPlats());
    }
  }

  resetNewPlat() {
    this.newPlat = {
      name: '',
      description: '',
      image: '',
      available: true,
      quantity: 1,
      dishType: 'PRINCIPAL',
      menuName: '',
      stockNames: []
    };
    this.stockMap = {};
  }
  onStockInput(name: string, event: Event) {
    const input = event.target as HTMLInputElement;
    const value = input?.value || '';
    this.handleStockChange(name, value);
  }
  
}
