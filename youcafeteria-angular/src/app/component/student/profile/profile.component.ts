import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../header/header.component';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, HeaderComponent, FormsModule],
  templateUrl: './profile.component.html'
})
export class ProfileComponent {
  isEditing: boolean = false;

  profile = {
    fullName: "John Doe",
    email: "john.doe@university.edu",
    studentId: "S12345678",
    department: "Computer Science",
    year: "3rd Year",
    dietaryPreferences: "No nuts, vegetarian options",
  };

  toggleEdit() {
    this.isEditing = !this.isEditing;
  }

  saveProfile() {
    alert("Profile updated successfully!");
    this.isEditing = false;
  }
}
