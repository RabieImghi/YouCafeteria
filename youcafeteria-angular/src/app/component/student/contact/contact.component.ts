import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../header/header.component';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [CommonModule, HeaderComponent, FormsModule],
  templateUrl: './contact.component.html'
})
export class ContactComponent {
  contactForm = {
    name: '',
    email: '',
    message: ''
  };

  isSubmitted: boolean = false;

  submitForm() {
    if (this.contactForm.name && this.contactForm.email && this.contactForm.message) {
      this.isSubmitted = true;
      setTimeout(() => {
        this.isSubmitted = false;
        this.contactForm = { name: '', email: '', message: '' };
      }, 3000);
    } else {
      alert('Please fill in all fields.');
    }
  }
}
