import { DataService } from 'src/app/services/data.service';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MenuController, NavController } from '@ionic/angular';
import { GlobalsService } from 'src/app/services/globals.service';
import { AlertController } from '@ionic/angular';
import { async } from '@angular/core/testing';



@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.page.html',
  styleUrls: ['./login-user.page.scss'],
})
export class LoginUserPage {

  validations_form: FormGroup;
  email:string;
  email_doesnt_exists:string = "";
  constructor(private menuCtrl:MenuController,
    public formBuilder:FormBuilder,
    private http:HttpClient,
    private globals:GlobalsService,
    private navCtrl:NavController,
    private dataService:DataService,
    private alertCtrl:AlertController
    ) {
      this.menuCtrl.enable(false);
      this.validations_form = this.formBuilder.group({
          email: ['', Validators.compose([
          Validators.required,
          Validators.pattern('^[A-Za-z0-9._%+-]{3,}@[a-zA-Z]{3,}([.]{1}[a-zA-Z]{2,}|[.]{1}[a-zA-Z]{2,}[.]{1}[a-zA-Z]{2,})')
        ])],
      });
    }

    validation_messages = {
      'email': [
        { type: 'required', message: 'Email is required.' },
        { type: 'pattern', message: 'Please enter a valid email.' }
      ],
    };

    onSubmit(){
      this.http.post(this.globals.userExistApiUrl,this.email).subscribe(
        async res => {
          if(res==true){
            this.dataService.setUser(this.email);
            
            const alert = await this.alertCtrl.create({
              cssClass: 'my-custom-class',
              header: 'Perfetto',
              message: 'LogIn eseguito.',
              buttons: ['OK']
            });
          
            await alert.present();
            this.back();
        }else{
            this.email_doesnt_exists = this.email+" : email doesn't exists";
        }
        }, 
        err => { 
          console.log(err);
          console.log('oops some error in User'); 
        }
      );
    }

    back(){
      this.navCtrl.navigateRoot(['/home'], { queryParams: { 'refresh': 1 } });
      this.menuCtrl.enable(true);
    }

}
