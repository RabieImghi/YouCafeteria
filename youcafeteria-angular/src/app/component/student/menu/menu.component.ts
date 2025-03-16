import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { RouterModule } from '@angular/router';  // Import RouterModule
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [
    HeaderComponent,
    CommonModule,
    RouterModule  // Add RouterModule here
  ],
  templateUrl: './menu.component.html'
})
export class MenuComponent {
  menus = [
    {
      title: 'Monday Menu',
      description: 'Start your week with our energy-boosting menu options.',
      image: 'https://images.unsplash.com/photo-1547592180-85f173990554?ixlib=rb-4.0.3&auto=format&fit=crop&w=400&h=300&q=80',
      route: '/menu/detail'
    },
    {
      title: 'Tuesday Menu',
      description: 'Enjoy our international cuisine day with dishes from around the world.',
      image: 'https://images.unsplash.com/photo-1565299507177-b0ac66763828?ixlib=rb-4.0.3&auto=format&fit=crop&w=400&h=300&q=80',
      route: '/menu/detail'
    },
    {
      title: 'Wednesday Menu',
      description: 'Midweek specials featuring comfort food to keep you going.',
      image: 'https://images.unsplash.com/photo-1484980972926-edee96e0960d?ixlib=rb-4.0.3&auto=format&fit=crop&w=400&h=300&q=80',
      route: '/menu/detail'
    },
    {
      title: 'Thursday Menu',
      description: 'Protein-packed options to fuel your busy day.',
      image: 'https://images.unsplash.com/photo-1511690656952-34342bb7c2f2?ixlib=rb-4.0.3&auto=format&fit=crop&w=400&h=300&q=80',
      route: '/menu/detail'
    },
    {
      title: 'Friday Menu',
      description: 'End the week with our special international cuisine rotation.',
      image: 'https://images.unsplash.com/photo-1504674900247-0877df9cc836?ixlib=rb-4.0.3&auto=format&fit=crop&w=400&h=300&q=80',
      route: '/menu/detail'
    },
    {
      title: 'Saturday Menu',
      description: 'Weekend treats with extended breakfast hours and brunch options.',
      image: 'https://images.unsplash.com/photo-1493770348161-369560ae357d?ixlib=rb-4.0.3&auto=format&fit=crop&w=400&h=300&q=80',
      route: '/menu/detail'
    }
  ];
 }
