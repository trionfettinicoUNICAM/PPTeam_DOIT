import { MenuController } from '@ionic/angular';
import { Component } from '@angular/core';
import { Title }     from '@angular/platform-browser';


@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {

  constructor(private titleService: Title,private menuCtrl:MenuController) {
    // cambio il titolo del pagine
    this.titleService.setTitle("home");
    //attivo il menu 
    this.menuCtrl.enable(true);
  }

}