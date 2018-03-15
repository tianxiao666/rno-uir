import { Component, OnInit, Output, EventEmitter, Input, ElementRef, ViewChild } from '@angular/core';
import * as _ from 'lodash';
import { Parameter } from './get-parameter-value';
import { Http } from '@angular/http';
import { environment } from '../../../../environments/environment';
import { ExcelService } from '../../common/export.service';
import * as saveAs from 'file-saver';

@Component({
  selector: 'xdr-testshow',
  templateUrl: './table.component.html',
  styleUrls: ['./table.scss'],
})
export class TableComponent implements OnInit {

  public datalist: any[];
  public rowsOnPage = 10;
  public activePage = 1;
  public sortBy = 'startTime';
  public sortOrder = 'desc';
  public itemsTotal = 0;
  public exportId = [];
  public exportName = [];

  @ViewChild('notFoundData') public notFoundDataDiv: ElementRef;
  @ViewChild('programError') public programError: ElementRef;
  public isShownLoading: boolean = false;
  dataName = [];
  dataId = [];
  private env = environment;
  private _input: any;

  @Output() private paragram: EventEmitter<Parameter> = new EventEmitter();
  @Output() private pageChange: EventEmitter<Parameter> = new EventEmitter();


  constructor(private http: Http,
  private excelService: ExcelService) {
    this.excelService = excelService;
  }

  public ngOnInit(): void {
    this.dataParameter();
  }


  public loadData() {
    this.itemsTotal = (this._input)[1];
    this.activePage = (this._input)[2] + 1;
    this.datalist = _.orderBy((this._input)[0], this.sortBy, [this.sortOrder]);
    this.datalist = _.slice(this.datalist, 0, this.rowsOnPage);
  }

  public onPageChange(event) {
    this.rowsOnPage = event.rowsOnPage;
    this.activePage = event.activePage;
    // console.log(this.activePage );
    this.dataParameter();
    this.pageChange.emit();
  }

  private dataParameter() {
    const emit = new Parameter(
      this.rowsOnPage,
      this.activePage - 1,
    );
    this.paragram.emit(emit);
  }

  @Input()
  public set input(v: any) {
    this._input = v;
    if (v) {
      this.dataId = ['name', 'status', 'description', 'fileName', 'startTime', 'endTime', 'successRate'];
      this.dataName = ['测试名称', '测试状态', '测试描述', '测试文件', '测试提交时间', '测试完成时间', '测试准确率'];
      this.loadData();
    }
  }

  public get input(): any {
    return this._input;
  }

  private downloadResult(id) {
    location.href = `${this.env.host}/api/downloadTestResult?taskId=${id}`;
  }
}
