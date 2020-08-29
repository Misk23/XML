import { Component, OnInit } from '@angular/core';
import { ScientificPublicationService } from 'src/app/services/scientific-publication.service';
import { ScientificPublicationEditDTO } from 'src/app/interfaces/scientific-publication-edit-dto';
import {Chapter} from 'src/app/interfaces/chapter';
import { Paragraph } from 'src/app/interfaces/paragraph';
import { title } from 'process';
import { element, $ } from 'protractor';
import { Citation } from 'src/app/interfaces/citation';

@Component({
  selector: 'app-edit-publication',
  templateUrl: './edit-publication.component.html',
  styleUrls: ['./edit-publication.component.css']
})
export class EditPublicationComponent implements OnInit {


  public scientificPublicationEditDTO: ScientificPublicationEditDTO = {id: '', title: '', keywords: [], chapters: [], references: []};

  public idPublicationForEdit: string;

  constructor(private scientificPublicationService: ScientificPublicationService) { }

  ngOnInit(): void {
    const publicationId = localStorage.getItem('publicationIdForEdit');
    this.idPublicationForEdit = publicationId;
    this.getPublicationForEdit();
  }



  getPublicationForEdit(){
    this.scientificPublicationService.getPublicationForEdit(this.idPublicationForEdit).subscribe((sp: any) => {


      document.getElementById('title').innerText = sp.scientificPublication.metadata.title;

      const keywords = document.getElementById('keywords');
      sp.scientificPublication.metadata.keywords.forEach(element => {
        const keyword = document.createElement('li');
        keyword.innerText = element;
        keyword.contentEditable = 'true';
        keywords.appendChild(keyword);

      });

      let chapterNumber: number = 0;
      const chapters = document.getElementById('chapters');

      sp.scientificPublication.chapter.forEach(element => {
        const chapter = document.createElement('div');
        const title = document.createElement('h3');

        title.innerText = element.title;
        title.contentEditable = 'true';
        chapter.appendChild(title);

        chapterNumber += 1;
        chapter.id = chapterNumber.toString();
        title.id = 'chapterTitle ' + chapterNumber;

        let parNumber: number = 0;
        element.paragraph.forEach(p => {
          parNumber += 1;


          const par = document.createElement('p');
          par.setAttribute('name', 'paragraph ' + chapter.id);
          par.id = chapter.id + ' ' + parNumber.toString();
          par.innerText = p.text;
          par.contentEditable = 'true';

          if ('citation' in p && p.citation !== null && 'text' in p.citation && p.citation.text !== null){
              const citation  = document.createElement('p');
              const italic = document.createElement('i');
              italic.id = par.id + ' ' + p.citation.id;
              italic.setAttribute('name', 'citations');
              italic.innerText = p.citation.text;
              italic.contentEditable = 'true';
              citation.appendChild(italic);

              const citationLink = document.createElement('sup');
              const citationUrl = document.createElement('a');
              citationUrl.href = 'editPublication#citationId ' + p.citation.id;
              citationUrl.innerText = 'c' + p.citation.id;
              citationLink.appendChild(citationUrl);

              citation.appendChild(citationLink);
              par.appendChild(citation);
          }

          chapter.appendChild(par);
        });

        chapters.appendChild(chapter);
      });

      const references = document.getElementById('references');
      
      sp.scientificPublication.reference.forEach(element => {
        const row = document.createElement('tr');
        row.setAttribute('name', 'referenceNaCitate');
        row.id = 'row ' + element.citationId;

        const idTd = document.createElement('td');
        const idP = document.createElement('p');
        idP.id = 'citationId ' + element.citationId;
        idP.innerText = element.citationId;
        idTd.appendChild(idP);
        row.appendChild(idTd);

        const authorNamesTd = document.createElement('td');
        const authorNameTextArea = document.createElement('p');
        let autorovaImena: string = '';
        element.authorNames.forEach(authorName => {
          autorovaImena += authorName + ' ';
        });
        authorNameTextArea.id = 'authorNames ' + element.citationId;
        authorNameTextArea.contentEditable = 'true';
        authorNameTextArea.innerText = autorovaImena;
        authorNamesTd.appendChild(authorNameTextArea);
        row.appendChild(authorNamesTd);

        const titleTd = document.createElement('td');
        const titleP = document.createElement('p');
        titleP.id = 'title ' + element.citationId;
        titleP.contentEditable = 'true';
        titleP.innerText = element.publicationTitle;
        titleTd.appendChild(titleP);
        row.appendChild(titleTd);

        const yearTd = document.createElement('td');
        const yearP = document.createElement('p');
        yearP.id = 'year ' + element.citationId;
        yearP.contentEditable = 'true';
        yearP.innerText = element.year;
        yearTd.appendChild(yearP);
        row.appendChild(yearTd);

        const urlTd = document.createElement('td');
        const urlP = document.createElement('p');
        urlP.id = 'url ' + element.citationId;
        urlP.contentEditable = 'true';
        urlP.innerText = element.url;
        urlTd.appendChild(urlP);
        row.appendChild(urlTd);

        const removeTd = document.createElement('td');
        const removeButton = document.createElement('button');
        removeButton.id = 'remove ' + element.citationId;
        removeButton.setAttribute('name', 'removeCitation');
        removeButton.addEventListener('click',(e: Event) => this.removeCitation(removeButton.id.trim().split( ' ')[1]));
        removeButton.className = 'btn btn-primary';

        removeButton.innerText = 'remove';
        removeTd.appendChild(removeButton);
        row.appendChild(removeTd);


        document.getElementById('references').appendChild(row);
      });



  });
  }

  removeCitation(id: string): void {
    document.getElementById('references').removeChild(document.getElementById('row ' + id));
  }
 

  editPublication() {
    
      const k: string[] = [];
      document.getElementById('keywords').childNodes.forEach(element => {
          k.push(element.textContent);
      });

      const cha: Chapter[] = [];

      const numberOfChapters: number = document.getElementById('chapters').childElementCount;
      
      for (let i = 0; i < numberOfChapters; i++) {
        
        const poglavlje = document.getElementById((i + 1).toString());
        const paragrafi: Paragraph[] = [];

        document.getElementsByName('paragraph ' + poglavlje.id).forEach(paragraf => {
          
          if (paragraf.children.length > 0) {
            
            const citationId: string = paragraf.firstElementChild.firstElementChild.id;
            const idCitata: string = citationId.split(' ')[2];
            const c: Citation = {id: '', publicationTitle: '', authorNames: [],
            text: paragraf.firstElementChild.firstElementChild.innerHTML, year: 0, url: ''};

            c.id = document.getElementById('citationId ' + idCitata).innerText;
            c.publicationTitle = document.getElementById('title ' + idCitata).innerText;
            c.year = Number.parseInt(document.getElementById('year ' + idCitata).innerText);
            c.url = document.getElementById('url ' + idCitata).innerText;
            
            paragrafi.push({text: paragraf.textContent, citation: c});
          } else {
          
            
            paragrafi.push({text: paragraf.textContent});
          }
        
        });

        const currentChapter: Chapter = {title: document.getElementById('chapterTitle ' + poglavlje.id).innerText
        , paragraphs: paragrafi};
      

        cha.push(currentChapter);

      }

      const referencesCitation: Citation[] = [];

      document.getElementsByName('referenceNaCitate').forEach(red => {
      const autori = red.childNodes[1].textContent.trim().split(' ');
      console.log(autori);

      const refCitation: Citation = {id: red.childNodes[0].textContent,
       publicationTitle: red.childNodes[2].textContent.trim(),
      year: +red.childNodes[3].textContent,
       url: red.childNodes[4].textContent, authorNames: autori};

      referencesCitation.push(refCitation);

  });

      this.scientificPublicationEditDTO = {id: this.idPublicationForEdit,
         title: document.getElementById('title').innerText, keywords: k, chapters: cha,
      references: referencesCitation};

      console.log(referencesCitation);


      this.scientificPublicationService.editPublication(this.scientificPublicationEditDTO).subscribe((message: any) => {
        alert(message.message);
      });
  
  }

  addNewCitation(){
    const brojCitata = document.getElementsByName('referenceNaCitate').length;
    const idNovogCitata = brojCitata;

    const row = document.createElement('tr');
    row.setAttribute('name', 'referenceNaCitate');

    const idTd = document.createElement('td');
    const idP = document.createElement('p');
    idP.id = 'citationId ' + idNovogCitata;
    idP.innerText = idNovogCitata.toString();
    idTd.appendChild(idP);
    row.appendChild(idTd);

    const authorNamesTd = document.createElement('td');
    const authorNameTextArea = document.createElement('p');
    authorNameTextArea.id = 'authorNames ' + idNovogCitata;
    authorNameTextArea.contentEditable = 'true';
    authorNameTextArea.innerText = '';
    authorNamesTd.appendChild(authorNameTextArea);
    row.appendChild(authorNamesTd);


    const titleTd = document.createElement('td');
    const titleP = document.createElement('p');
    titleP.id = 'title ' + idNovogCitata;
    titleP.contentEditable = 'true';
    titleP.innerText = '';
    titleTd.appendChild(titleP);
    row.appendChild(titleTd);

    const yearTd = document.createElement('td');
    const yearP = document.createElement('p');
    yearP.id = 'year ' + idNovogCitata;
    yearP.contentEditable = 'true';
    yearP.innerText = '';
    yearTd.appendChild(yearP);
    row.appendChild(yearTd);

    const urlTd = document.createElement('td');
    const urlP = document.createElement('p');
    urlP.id = 'url ' + idNovogCitata;
    urlP.contentEditable = 'true';
    urlP.innerText = '';
    urlTd.appendChild(urlP);
    row.appendChild(urlTd);

    const removeTd = document.createElement('td');
    const removeButton = document.createElement('button');
    removeButton.id = 'remove ' + '';
    removeButton.className = 'btn btn-primary';

    removeButton.innerText = 'remove';
    removeTd.appendChild(removeButton);
    row.appendChild(removeTd);


    document.getElementById('references').appendChild(row);

  }
}