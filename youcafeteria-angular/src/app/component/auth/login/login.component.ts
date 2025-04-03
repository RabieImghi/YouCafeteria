import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../../service/auth.service';
import { StateService } from '../../../service/state.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule
  ],
  templateUrl: './login.component.html'
})
export class LoginComponent {

  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService, private stateService: StateService, private router: Router) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(4)]], // Username required with at least 4 characters
      password: ['', [Validators.required, Validators.minLength(6)]], // Password required with min 6 characters
    });
  }

  // Getter for form controls
  get f() {
    return this.loginForm.controls;
  }

  // Handle Form Submission
  onSubmit() {
    if (this.loginForm.invalid) {
      return;
    }
    this.authService.login(this.loginForm.value).subscribe(
      (response: any) => {
        this.stateService.setAuthToken(response.token);
        this.router.navigate(['']);

      },
      (error: any) => {
        console.log(error);
      }
    );
  }

}
