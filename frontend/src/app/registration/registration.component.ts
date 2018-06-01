import { Component, OnInit } from '@angular/core';
import { NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
class Registration{
  constructor(
    public firstName: string = '',
    public lastName: string = '',
    public email: string = '',
    public dob: NgbDateStruct = null,
    public userName: string = '',
    public country: string = 'Select country'

  ){}

}

export class RegistrationComponent implements OnInit {

  // maintain list of registration
  registrations: Registration[] = [];

  // maintain registration Model
  regModel: Registration;

  //maintain registration form display status
  showNew: Boolean = false;

  //either save or update
  submitType: string = 'Save';

  //maintain table row index
  selectedRow: number;

  //maintain arrya of countries
  countries: string[] = ['US', 'UK', 'UAW','India'];

  constructor() {
    this.registrations.push(new Registration('Johan', 'Peter',  'johan@gmail.com',{year: 1975, month: 12, day: 3},  'johan123', 'UK'));

    this.registrations.push(new Registration('Mohamed', 'Tariq',  'tariq@gmail.com', {year: 1975, month: 12, day: 3}, 'tariq123', 'UAE'));
    
    this.registrations.push(new Registration('Nirmal', 'Kumar','nirmal@gmail.com',{year: 1975, month: 12, day: 3},  'nirmal123', 'India'));
   }

   onNew(){

  }



  ngOnInit() {
  }

}
