import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Parameter } from './data-table/get-parameter-value';
import { SampleTrainService } from './sample-train.service';
import { FinalSearchValue } from './final-search-value';
import { SearchValue } from './train/search-value';
import { environment } from '../../../environments/environment';
import { Object } from '../common/Object';
import { JhiEventManager } from 'ng-jhipster';
@Component({
    selector: 'sample-train',
    templateUrl: './sample-train.component.html',
    styleUrls: ['./sample-train.component.scss'],
    providers: [],
})
export class SampleTrainComponent implements OnInit {


  @ViewChild('notFoundData') public notFoundDataDiv: ElementRef;
  @ViewChild('programError') public programError: ElementRef;

  public isShowLoading: boolean = false;
  public isShownTrainTable: boolean = false;
  private env = environment;
  private data: any;
  private dateFromCurr: any;
  private dateToCurr: any;
  private cityCurr: any;
  private trainNameCurr: any;
  private trainStatusCurr: any;
  private rowsOnPage = 10;
  private activePage = 0;
  private curDate = new Date();
  private condition: Object = {
    date: { year: this.curDate.getFullYear() - 1, month: this.curDate.getMonth() + 1, day: this.curDate.getDate() }};
  private condition2: Object = {
    date: { year: this.curDate.getFullYear(), month: this.curDate.getMonth() + 1, day: this.curDate.getDate() }};
  constructor(private trainService: SampleTrainService,
  private eventManager: JhiEventManager) {
    const e = new SearchValue('广州', '', '', this.condition, this.condition2);
    this.activePage = 0;
    this.rowsOnPage = 10;
    this.searchTrainData(e);
  }
  ngOnInit(): void {
    this.eventManager.subscribe('addTrainTaskSuccess', (message) => {
      const e = new SearchValue('广州', '', '', this.condition, this.condition2);
      this.activePage = 0;
      this.rowsOnPage = 10;
      this.searchTrainData(e);
    });
  }
  public searchParameterData(e: Parameter) {
    if (this.rowsOnPage) {
      if (this.rowsOnPage !== e.rowsOnPage) {
        this.activePage = 0;
        this.rowsOnPage = e.rowsOnPage;
      }else {
        this.activePage = e.activePage;
      }
    }else {
      this.activePage = e.activePage;
      this.rowsOnPage = e.rowsOnPage;
    }
  }

  public pageParameterData() {
    const final = new FinalSearchValue(
      this.cityCurr,
      this.trainNameCurr,
      this.trainStatusCurr,
      this.dateFromCurr,
      this.dateToCurr,
      this.rowsOnPage,
      this.activePage,
      'query',
    );
    this.getData(final);
  }

  searchTrainData(e: SearchValue) {
    this.cityCurr = e.city;
    this.trainNameCurr = e.trainName;
    this.trainStatusCurr = e.trainStatus;
    this.dateFromCurr = e.dateFrom;
    this.dateToCurr = e.dateTo;
    const final = new FinalSearchValue(
      this.cityCurr,
      this.trainNameCurr,
      this.trainStatusCurr,
      this.dateFromCurr,
      this.dateToCurr,
      this.rowsOnPage,
      this.activePage,
      'query',
    );
    this.getData(final);
  }

  public getData(final: FinalSearchValue) {
    this.isShowLoading = true;
    this.trainService.getData(final).then( data => {
      this.isShowLoading = false;
      const dataFinal = [];
      if (data === null || data.json().length === 0) {
        this.activePage = 0;
        setTimeout(() => {
          this.notFoundDataDiv.nativeElement.style.display = 'block';
        }, 0);
        setTimeout(() => {
          this.notFoundDataDiv.nativeElement.style.display = 'none';
        }, 3000);
        this.isShownTrainTable = false;
        return;
      } else {
        for (const i of data) {
          i.startTime = i.startTime.substr(0, 18);
          i.endTime = i.endTime.substr(0, 18);
        }
        dataFinal.push(data.json());
        dataFinal.push(data.headers.get('X-Total-Count'));
        dataFinal.push(this.activePage);
        this.data = dataFinal;
        this.isShownTrainTable = true;
      }
    }).catch(e => {
      // console.log(e);
      this.isShowLoading = false;
      setTimeout(() => {
        this.programError.nativeElement.style.display = 'block';
      }, 0);
      setTimeout(() => {
        this.programError.nativeElement.style.display = 'none';
      }, 3000);
      return;
    });
  }
}
