import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent} from '../home/home.component';
import { RegistrationComponent} from '../registration/registration.component';
import { RouterModule, Routes} from '@angular/router';
const routes : Routes = [
  {path:'', component: HomeComponent},
  {path: 'registration', component:RegistrationComponent}
]
@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports:[
    RouterModule
  ],
  declarations: []
})
export class AppRoutingModule { }
