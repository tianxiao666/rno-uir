import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import * as _ from 'lodash';
import { Parameter } from './get-parameter-value';

@Component({
  selector: 'xdr-search-tableshow',
  templateUrl: './table.component.html',
  styleUrls: ['./table.scss'],
})
export class TableComponent implements OnInit {

  public datalist: any[];
  public rowsOnPage = 10;
  public activePage = 1;
  public sortBy = '1';
  public sortOrder = 'desc';
  public itemsTotal = 0;
  private dataId: any;
  private dataName: any;
  private _input: any;

  @Output() private paragram: EventEmitter<Parameter> = new EventEmitter();
  @Output() private pageChange: EventEmitter<Parameter> = new EventEmitter();
  @Output() private chartChange: EventEmitter<string> = new EventEmitter();

  public ngOnInit(): void {
    this.dataParameter();
  }

  showEachChart(c: string) {
    this.chartChange.emit(c);
  }

  public loadData() {
    this.itemsTotal = (this._input)[1];
    this.activePage = (this._input)[2] + 1;
    this.datalist = _.orderBy((this._input)[0], this.sortBy, [this.sortOrder]);
    this.datalist = _.slice(this.datalist, 0, this.rowsOnPage);
  }

  public toInt(num: string) {
    return +num;
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
      if (v[3] === 'checked') {
        const id = [];
        for (let i=0; i<116; i++) {
          id.push(i);
        }
        this.dataId = id;
        const name = ['id', '干扰日期','地市', '设备', '干扰分类', '小区名称', '小区', '所属频段', 'ICM1', 'ICM2', 'ICM3', 'ICM4', 'ICM5', '最大值', '平均值', '干扰系数'];
        for (let j=0; j<100; j++) {
          name.push('PRB'+j);
        };
        this.dataName = name;
      }else {
        this.dataId = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15'];
        this.dataName = ['id', '干扰日期','地市', '设备', '干扰分类', '小区名称', '小区', '所属频段', 'ICM1', 'ICM2', 'ICM3', 'ICM4', 'ICM5', '最大值', '平均值', '干扰系数'];
      }
      this.loadData();
    }
  }

  public get input(): any {
    return this._input;
  }

}
