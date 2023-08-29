import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'aliv-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.scss']
})
export class IndexComponent implements OnInit {


  public isTrue = 1;
  constructor() { }

  ngOnInit() {
  }

}
