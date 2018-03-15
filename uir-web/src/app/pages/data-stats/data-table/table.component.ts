import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import * as _ from 'lodash';
import { Parameter } from './get-parameter-value';

@Component({
  selector: 'xdr-statistics-tableshow',
  templateUrl: './table.component.html',
  styleUrls: ['./table.scss'],
})
export class TableComponent implements OnInit {

  public datalist: any[];
  public rowsOnPage = 10;
  public activePage = 1;
  public sortBy = '';
  public sortOrder = 'asc';
  public itemsTotal = 0;
  private dataId: any;
  private dataName: any;
  private _input: any;


  public ngOnInit(): void {
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

  }


  @Input()
  public set input(v: any) {
    this._input = v;
    if (v) {
      this.dataId = ['0', '1'];
      this.dataName = ['干扰类型', '小区数'];
      this.loadData();
    }
  }

  public get input(): any {
    return this._input;
  }

}
