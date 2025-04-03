import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { UserService } from '../../../service/user.service';
interface User {
  id: number;
  username: string;
  firstName: string;
  lastName: string;
  email: string;
  role: string;
}

@Component({
  selector: 'app-users-table',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './users.component.html',
})
export class UsersComponent {
  users: User[] = [];
  showAddForm = false;
  editUserData: any = null;
  showEditForm = false;

  newUser = {
    username: '',
    firstName: '',
    lastName: '',
    password: '',
    email: '',
    role: 'USER'
  };
  constructor(private userService: UserService) {}

  ngOnInit() {
    this.loadUsers();
  }

  loadUsers() {
    this.userService.getUsers().subscribe((response) => {
      this.users = response.content;
      console.log(this.users);
    });
  }

  addUser() {
    this.userService.createUser(this.newUser).subscribe({
      next: () => {
        this.loadUsers();
        this.showAddForm = false;
        this.resetForm();
      },
      error: (err) => console.error(err)
    });
  }

  resetForm() {
    this.newUser = {
      username: '',
      firstName: '',
      lastName: '',
      password: '',
      email: '',
      role: 'USER'
    };
  }

  deleteUser(username: string) {
    if (confirm(`Are you sure you want to delete user "${username}"?`)) {
      this.userService.deleteUser(username).subscribe({
        next: () => this.loadUsers(),
        error: (err) => console.error(err)
      });
    }
  }
  
  openEditModal(user: User) {
    this.editUserData = { ...user };
    this.showEditForm = true;
  }
  
  updateUser() {
    const userToUpdate = { ...this.editUserData };
    delete userToUpdate.password; 
  
    this.userService.updateUser(userToUpdate).subscribe({
      next: () => {
        this.loadUsers();
        this.showEditForm = false;
        this.editUserData = null;
      },
      error: (err) => console.error(err)
    });
  }
  
}
