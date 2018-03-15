import { Component, ViewChild, Input, Output, EventEmitter, ElementRef, OnInit } from '@angular/core';
import { SelectItem } from '../../common/select-item';
import { SearchValue } from './search-value';
import { IMyDpOptions } from 'mydatepicker';
import { Object } from '../../common/Object';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Principal } from '../../auth/principal.service';
import { environment } from '../../../../environments/environment';
import { Response } from '@angular/http';

export class Mes {
  area: string;
  type: string;
  author: string;
}

@Component({
  selector: 'xdr-import-condition',
  templateUrl: './condition.component.html',
  styleUrls: ['./condition.component.css'],
})

export class ConditionComponent implements OnInit {

  @Output() public search: EventEmitter<SearchValue> = new EventEmitter();
  @Output() public typeErr: EventEmitter<any> = new EventEmitter();
  @Output() public importMes: EventEmitter<Response> = new EventEmitter();
  @Output() public fileWarn: EventEmitter<any> = new EventEmitter();

  @Input() multiple: boolean = false;
  @ViewChild('fileInput') inputEl: ElementRef;

  private myDatePickerOptions: IMyDpOptions = {
    // other options...
    dateFormat: 'yyyy-mm-dd',
  };

  private author: any;

  private env = environment;
  private tmpUrl;

  // Initialized to specific date (09.10.2018).
  private curDate = new Date();
  // Initialized to specific date (09.10.2018).
  private model1: Object = { date: { year: 2017, month: 8, day: 22 } };
  private model2: Object = {
    date: { year: this.curDate.getFullYear(), month: this.curDate.getMonth() + 1, day: this.curDate.getDate() + 1 }};

  private conditions = new SearchValue(
    this.model1,
    this.model2,
    '',
    '干扰数据');

  private OnAreaSetI(e: string[]) {
    if(e.length===3) {
      this.conditions.area = e[2];
    }else {
      this.conditions.area = e[1];
    }
  }

  private _interfaces: SelectItem[] = [
    { label: '干扰数据', value: '干扰数据' },
    { label: '干扰数据训练样本', value: '干扰数据训练样本' },
    { label: '干扰数据测试样本', value: '干扰数据测试样本' },
  ];

  private formData: any;

  private fileFlag: boolean = false;

  private message: Mes = {
    area: this.conditions.area,
    type: this.conditions.interfereType,
    author: '',
  };

  constructor(private http: Http, private principal: Principal) {
    this.principal.identity(true).then((account) => {
      this.author = account.login;
      this.message.author = this.author;
    });
  }

  uploadSelect() {
    const inputEl: HTMLInputElement = this.inputEl.nativeElement;
    const fileCount: number = inputEl.files.length;
    this.formData = new FormData();
    let key = this.conditions.interfereType;
    if (fileCount > 0) {
      for (let i = 0; i < fileCount; i++) {
        if (inputEl.files.item(i).type === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet') {
          this.fileFlag = true;
          key += i.toString();
          this.formData.append(key, inputEl.files.item(i));
        }else {
          this.typeErr.emit();
          this.inputEl.nativeElement.value = null;
          this.fileFlag = false;
          return;
        }
      }
    }
  }

  import() {
    if (this.fileFlag === false) {
      this.fileWarn.emit();
    }else {
      this.tmpUrl = '/api/_import/data-imports-message';
      const urlM = `${this.env.host}/${this.tmpUrl}`;
      this.message.area = this.conditions.area;
      this.message.type = this.conditions.interfereType;
      this.http.post(urlM, this.message)
        .map(res => '')
        .catch(error => Observable.throw(error))
        .subscribe(
          data => console.info('success'),
          error => console.info(error),
        );
      this.tmpUrl = '/api/_import/data-imports';
      const urlI = `${this.env.host}/${this.tmpUrl}`;
      this.http.post(urlI, this.formData)
        .map(res => this.showMess(res))
        .catch(error => Observable.throw(error))
        .subscribe(
          data => console.info('success'),
          error => console.info(error),
        );
      /*this.inputEl.nativeElement.value = null;
      this.fileFlag = false;*/
    }
  }

  public ngOnInit(): void {

  }

  public searchDetailClick(e: MouseEvent) {
    this.search.emit(this.conditions);
  }

  public showMess(m: Response) {
    this.importMes.emit(m);
  }

}
