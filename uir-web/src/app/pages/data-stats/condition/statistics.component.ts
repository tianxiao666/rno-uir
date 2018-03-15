import {Component, Output, EventEmitter, OnInit, Input} from '@angular/core';
import { SelectItem } from '../../common/select-item';
import { SearchValue } from './search-value';
import { IMyDpOptions } from 'mydatepicker';
import { Object } from '../../common/Object';

@Component({
  selector: 'xdr-statistics-condition',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css'],
})

export class ConditionComponent implements OnInit {

  @Output() public statisticssearch: EventEmitter<SearchValue> = new EventEmitter();
  @Output() public analysissearch: EventEmitter<SearchValue> = new EventEmitter();

  private myDatePickerOptions: IMyDpOptions = {
    // other options...
    dateFormat: 'yyyy-mm-dd',
  };
  private curDate = new Date();
  // Initialized to specific date (09.10.2018).
  private model1: Object = { date: { year: 2016, month: 7, day: 9 } };
  private model2: Object = {
    date: { year: this.curDate.getFullYear(), month: this.curDate.getMonth() + 1, day: this.curDate.getDate() + 1 }};
  // Initialized to specific date (09.10.2018).

  private statisticecondition = new SearchValue(
    this.model1,
    this.model2,
    '440100',
    '全部',
    'table',
    '',
    '');

  private OnAreaSetT(e: string[]) {
    if(e.length===3) {
      this.statisticecondition.area = e[2];
    }else {
      this.statisticecondition.area = e[1];
    }
  }

  private _device: SelectItem[] = [
    { label: '', value: '全部' },
    { label: '华为', value: '华为' },
    { label: '中兴', value: '中兴' },
    { label: '爱立信', value: '爱立信' },
  ];

  ngOnInit(): void {

  }

  public statisticsClick(e: MouseEvent) {
    this.statisticecondition.selectValue = 'statistics';
    this.statisticssearch.emit(this.statisticecondition);
  }

  public analysisClick(e: MouseEvent) {
    this.statisticecondition.showType = 'chart';
    this.statisticecondition.selectValue = 'analysis';
    this.analysissearch.emit(this.statisticecondition);
  }

}
