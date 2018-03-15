import {Component, Output, EventEmitter, OnInit, Input} from '@angular/core';
import { SelectItem } from '../../common/select-item';
import { SearchValue } from './search-value';
import { IMyDpOptions } from 'mydatepicker';
import { Object } from '../../common/Object';

@Component({
  selector: 'xdr-search-condition',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
})

export class ConditionComponent implements OnInit {

  @Output() public search: EventEmitter<SearchValue> = new EventEmitter();
  @Output() public export: EventEmitter<SearchValue> = new EventEmitter();

  private myDatePickerOptions: IMyDpOptions = {
    // other options...
    dateFormat: 'yyyy-mm-dd',
  };
  private curDate = new Date();
  // Initialized to specific date (09.10.2018).
  private model1: Object = { date: { year: 2016, month: 5, day: 22 } };
  private model2: Object = {
    date: { year: this.curDate.getFullYear(), month: this.curDate.getMonth() + 1, day: this.curDate.getDate() +1 }};

  private searchcondition = new SearchValue(
    this.model1,
    this.model2,
    '',
    '全部',
    '全部',
    '',
    '',
    '');

  private _devices: SelectItem[] = [
    { label: '', value: '全部' },
    { label: '华为', value: '华为' },
    { label: '中兴', value: '中兴' },
    { label: '爱立信', value: '爱立信' },
  ];

  private _assortment: SelectItem[] = [
    { label: '', value: '全部' },
    { label: '超强干扰', value: '超强干扰' },
    { label: '特强干扰', value: '特强干扰' },
    { label: '强干扰', value: '强干扰' },
    { label: '一般干扰', value: '一般干扰' },
    { label: '弱干扰', value: '弱干扰' },
    { label: '无干扰', value: '无干扰' },
  ];

  ngOnInit(): void {
  }

  private OnAreaSetS(e: string[]) {
    if(e.length===3) {
      this.searchcondition.area = e[2];
    }else {
      this.searchcondition.area = e[1];
    }
  }

  public setSelected() {
    if (this.searchcondition.showPRB) {
      this.searchcondition.showPRB = '';
    }else {
      this.searchcondition.showPRB = 'checked';
    }
  }

  public searchClick(e: MouseEvent) {
    this.search.emit(this.searchcondition);
  }

  public exportClick(e: MouseEvent) {
    this.export.emit(this.searchcondition);
  }

}
