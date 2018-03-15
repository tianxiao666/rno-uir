import { Component , EventEmitter, OnInit, Output} from '@angular/core';
import { IMyDpOptions } from 'mydatepicker';
import { NgbModal , NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import { ClassifyAddComponent } from '../classify-add/classify-add.component';
import { SearchValue } from './search-value';
import { Object } from '../../common/Object';
@Component({
  selector: 'uir-classify',
  templateUrl: './classify.component.html',
})

export class ClassifyComponent implements OnInit {

  @Output() public search: EventEmitter<SearchValue> = new EventEmitter();
  public curDate = new Date();
  private myDatePickerOptions: IMyDpOptions = {
    dateFormat: 'yyyy-mm-dd',
  };
  private condition: Object = {
    date: { year: this.curDate.getFullYear() - 1, month: this.curDate.getMonth() + 1, day: this.curDate.getDate() }};
  private condition2: Object = {
    date: { year: this.curDate.getFullYear(), month: this.curDate.getMonth() + 1, day: this.curDate.getDate() }};
  private classify = new SearchValue('广州', '' , '', this.condition, this.condition2);
  private isOpen = false;
  constructor(
    private modalService: NgbModal,
  ) {}

  add(): NgbModalRef {
    if (this.isOpen) {
      return;
    }
    this.isOpen = true;
    const modalRef = this.modalService.open(ClassifyAddComponent);
    modalRef.result.then((result) => {
      this.isOpen = false;
    }, (reason) => {
      this.isOpen = false;
    });
  }
  ngOnInit(): void {
  }

  searchClassifyClick(e: MouseEvent) {
    const emit = new SearchValue (
      this.classify.city,
      this.classify.classifyName,
      this.classify.classifyStatus,
      this.classify.dateFrom,
      this.classify.dateTo,
    );
    this.search.emit(emit);
  }

}

