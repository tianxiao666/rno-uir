import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Parameter } from './data-table/get-parameter-value';
import { FinalSearchValue } from './final-search-value';
import { SearchValue } from './test/search-value';
import { SampleTestService } from './sample-test.service';
import { Object } from '../common/Object';
import { JhiEventManager } from 'ng-jhipster';
@Component({
  selector: 'sample-test',
  templateUrl: './sample-test.component.html',
  styleUrls: ['./sample-test.component.scss'],
  providers: [],
})
export class SampleTestComponent implements OnInit{

  @ViewChild('notFoundData') public notFoundDataDiv: ElementRef;
  @ViewChild('programError') public programError: ElementRef;

  public isShowLoading: boolean = false;
  public isShownTestTable: boolean = false;

  private data: any;
  private dateFromCurr: any;
  private dateToCurr: any;
  private cityCurr: any;
  private testNameCurr: any;
  private testStatusCurr: any;
  private rowsOnPage: any;
  private activePage: any;
  private curDate = new Date();
  private condition: Object = {
    date: { year: this.curDate.getFullYear() - 1, month: this.curDate.getMonth() + 1, day: this.curDate.getDate() }};
  private condition2: Object = {
    date: { year: this.curDate.getFullYear(), month: this.curDate.getMonth() + 1, day: this.curDate.getDate() }};

  constructor(private testService: SampleTestService,
              private eventManager: JhiEventManager) {
    const e = new SearchValue('广州', '', ' ', this.condition, this.condition2);
    this.activePage = 0;
    this.rowsOnPage = 10;
    this.searchTestData(e);
  }

  ngOnInit(): void {
    this.eventManager.subscribe('addTestTaskSuccess', (message) => {
      const e = new SearchValue('广州', '', '', this.condition, this.condition2);
      this.activePage = 0;
      this.rowsOnPage = 10;
      this.searchTestData(e);
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
      this.testNameCurr,
      this.testStatusCurr,
      this.dateFromCurr,
      this.dateToCurr,
      this.rowsOnPage,
      this.activePage,
      'query',
    );
    this.getData(final);
  }

  searchTestData(e: SearchValue) {
    this.cityCurr = e.city;
    this.testNameCurr = e.testName;
    this.testStatusCurr = e.testStatus;
    this.dateFromCurr = e.dateFrom;
    this.dateToCurr = e.dateTo;
    const final = new FinalSearchValue(
      this.cityCurr,
      this.testNameCurr,
      this.testStatusCurr,
      this.dateFromCurr,
      this.dateToCurr,
      this.rowsOnPage,
      this.activePage,
      'query',
    );
    this.isShowLoading = true;
    this.getData(final);
  }

  getData(final: FinalSearchValue) {
    this.testService.getData(final).then( data => {
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
        this.isShownTestTable = false;
        return;
      } else {
        // console.log(data);
        dataFinal.push(data.json());
        dataFinal.push(data.headers.get('X-Total-Count'));
        dataFinal.push(this.activePage);
        this.data = dataFinal;
        this.isShownTestTable = true;
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

