import { Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import { Parameter } from './data-table/get-parameter-value';
import { SampleClassifyService } from './sample-classify.service';
import { FinalSearchValue } from './final-search-value';
import { SearchValue } from './classify/search-value';
import { Object } from '../common/Object';
import { JhiEventManager } from 'ng-jhipster';
@Component({
  selector: 'sample-classify',
  templateUrl: './sample-classify.component.html',
  styleUrls: ['./sample-classify.component.scss'],
  providers: [],
})
export class SampleClassifyComponent implements OnInit{

  @ViewChild('notFoundData') public notFoundDataDiv: ElementRef;
  @ViewChild('programError') public programError: ElementRef;

  public isShowLoading: boolean = false;
  public isShownClassifyTable: boolean = false;

  private data: any;
  private dateFromCurr: any;
  private dateToCurr: any;
  private provinceCurr: any;
  private cityCurr: any;
  private classifyNameCurr: any;
  private classifyStatusCurr: any;
  private rowsOnPage: any;
  private activePage: any;
  private curDate = new Date();
  private condition: Object = {
    date: { year: this.curDate.getFullYear() - 1, month: this.curDate.getMonth() + 1, day: this.curDate.getDate() }};
  private condition2: Object = {
    date: { year: this.curDate.getFullYear(), month: this.curDate.getMonth() + 1, day: this.curDate.getDate() }};


  constructor(private classifyService: SampleClassifyService,
              private eventManager: JhiEventManager) {
    const e = new SearchValue('广州', '', '', this.condition, this.condition2);
    this.searchClassifyData(e);
  }

  ngOnInit(): void {
    this.eventManager.subscribe('addClassifyTaskSuccess', (message) => {
      const e = new SearchValue('广州', '', '', this.condition, this.condition2);
      this.activePage = 0;
      this.rowsOnPage = 10;
      this.searchClassifyData(e);
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
      this.classifyNameCurr,
      this.classifyStatusCurr,
      this.dateFromCurr,
      this.dateToCurr,
      this.rowsOnPage,
      this.activePage,
      'query',
    );
    this.getData(final);
  }

  searchClassifyData(e: SearchValue) {
    this.cityCurr = e.city;
    this.classifyNameCurr = e.classifyName;
    this.classifyStatusCurr = e.classifyStatus;
    this.dateFromCurr = e.dateFrom;
    this.dateToCurr = e.dateTo;
    const final = new FinalSearchValue(
      this.cityCurr,
      this.classifyNameCurr,
      this.classifyStatusCurr,
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
    this.classifyService.getData(final).then( data => {
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
        this.isShownClassifyTable = false;
        return;
      } else {
        // console.log(data);
        dataFinal.push(data.json());
        dataFinal.push(data.headers.get('X-Total-Count'));
        dataFinal.push(this.activePage);
        this.data = dataFinal;
        this.isShownClassifyTable = true;
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

