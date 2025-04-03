  import { Component, OnInit } from '@angular/core';
  import { CommonModule } from '@angular/common';
  import { RouterModule } from '@angular/router';
  import { HeaderComponent } from '../header/header.component';
  import { Router } from '@angular/router';
  import { PlatService } from '../../../service/plat.service'; 
  import { ReservationService } from '../../../service/reservation.service';
  import { StateService } from '../../../service/state.service';
  interface Dish {
    name: string;
    description: string;
    quantity: number;
    available: boolean;
    dishType: string;
  }
  @Component({
    selector: 'app-reservation',
    standalone: true,
    imports: [CommonModule, RouterModule, HeaderComponent],
    templateUrl: './reservation.component.html'
  })
  export class ReservationComponent implements OnInit {

    todayDate: string = '';
    userReservations: any[] = [];

    constructor(
      private router: Router,
      private platService: PlatService,
      private reservationService: ReservationService,
      private stateService: StateService
    ) {}

    menu: any;
    wantToReserve = false;

    todayMenu: { mainDishes: Dish[]; salads: Dish[]; desserts: Dish[]; } = {
      mainDishes: [],
      salads: [],
      desserts: []
    };

    selectedMainDish: any = null;
    selectedSalad: any = null;
    selectedDessert: any = null;

    plats: any[] = [];
    principalDishes: any[] = [];
    dessertDishes: any[] = [];
    starterDishes: any[] = [];

    ngOnInit() {
      this.menu = history.state.menu;
      if(this.menu){
        this.wantToReserve = true;
        this.loadDishesForMenu(this.menu.name);
      }

      const today = new Date();
      this.todayDate = today.toLocaleDateString('en-US', { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' });

      this.loadUserReservations();
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

        this.todayMenu.mainDishes = this.principalDishes;
        this.todayMenu.salads = this.starterDishes;
        this.todayMenu.desserts = this.dessertDishes;
      });
    }

    selectDish(category: string, dish: any) {
      if (category === 'main') this.selectedMainDish = dish;
      if (category === 'salad') this.selectedSalad = dish;
      if (category === 'dessert') this.selectedDessert = dish;
    }

    reserveMeal() {
      const tomorrow = new Date();
      tomorrow.setDate(tomorrow.getDate() + 1);
      const dishName = this.selectedMainDish?.name;

      if (!dishName) {
        alert('Please select a main dish.');
        return;
      }

      this.reservationService.createReservation({
        reservationDate: tomorrow.toISOString(),
        dishName: dishName
      }).subscribe({
        next: () => {
          alert('Reservation successfully made!');
          this.loadUserReservations();
          this.selectedMainDish = null;
          this.selectedSalad = null;
          this.selectedDessert = null;
        },
        error: (err :any) => {
          console.error(err);
          alert('Failed to reserve.'+err.error);
        }
      });
    }

    loadUserReservations() {
      const username = this.stateService.getUsername();
      this.reservationService.getUserReservations(username).subscribe({
        next: (res: any) => {
          this.userReservations = res.content;
          console.log(this.userReservations);
        },
        error: (err:any) => {
          console.error(err);
          alert('Could not load reservations.');
        }
      });
    }

    cancelReservation(id: number) {
      this.reservationService.cancelReservation(id).subscribe(() => {
        alert('Reservation cancelled');
        this.loadUserReservations();
      });
    }
  }
  