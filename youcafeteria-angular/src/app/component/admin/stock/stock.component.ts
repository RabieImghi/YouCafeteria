import { Component, OnInit } from '@angular/core';
import { StockService } from '../../../service/stock.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-stock',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './stock.component.html'
})
export class StockComponent implements OnInit {
  stocks: any[] = [];
  showAddForm = false;
  showEditForm = false;
  newStock: any = {
    name: '',
    description: '',
    quantity: 0,
    creationDate: new Date().toISOString().slice(0, 16) // YYYY-MM-DDTHH:mm
  };
  editStock: any = null;

  constructor(private stockService: StockService) {}

  ngOnInit() {
    this.loadStocks();
  }

  loadStocks() {
    this.stockService.getAllStocks().subscribe(res => {
      this.stocks = res.content;
    });
  }

  createStock() {
    const payload = {
      ...this.newStock,
      creationDate: new Date(this.newStock.creationDate).toISOString()
    };
    this.stockService.createStock(payload).subscribe(() => {
      this.loadStocks();
      this.resetNewStock();
      this.showAddForm = false;
    });
  }

  openEditModal(stock: any) {
    this.editStock = {
      ...stock,
      creationDate: new Date(stock.creationDate).toISOString().slice(0, 16)
    };
    this.showEditForm = true;
  }

  updateStock() {
    const payload = {
      ...this.editStock,
      creationDate: new Date(this.editStock.creationDate).toISOString()
    };
    this.stockService.updateStock(payload).subscribe(() => {
      this.loadStocks();
      this.showEditForm = false;
    });
  }

  deleteStock(name: string) {
    if (confirm(`Delete stock '${name}'?`)) {
      this.stockService.deleteStock(name).subscribe(() => this.loadStocks());
    }
  }

  resetNewStock() {
    this.newStock = {
      name: '',
      description: '',
      quantity: 0,
      creationDate: new Date().toISOString().slice(0, 16)
    };
  }
}
