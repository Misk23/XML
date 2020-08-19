import { Component, OnInit, Input } from '@angular/core';


@Component({
    selector: 'app-user-publication',
    templateUrl: './htmlContent.html',
  })
export class HtmlContentComponent implements OnInit {

    htmlContent: string;

    constructor() { }

    ngOnInit(): void {
        this.htmlContent = localStorage.getItem('publicationHtmlContent');
    }

}