import { Component, EventEmitter, OnInit, Output} from '@angular/core';
import { IMyDpOptions } from 'mydatepicker';
import { NgbModal , NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import { TrainAddComponent } from '../train-add/train-add.component';
import { SearchValue } from './search-value';
import { Object } from '../../common/Object';
@Component({
  selector: 'uir-train',
  templateUrl: './train.component.html',
})

export class TrainComponent implements OnInit {

  @Output() public search: EventEmitter<SearchValue> = new EventEmitter();

  public myDatePickerOptions: IMyDpOptions = {
    dateFormat: 'yyyy-mm-dd',
  };
  public curDate = new Date();
  public condition: Object = {
    date: { year: this.curDate.getFullYear() - 1, month: this.curDate.getMonth() + 1, day: this.curDate.getDate() }};
  public condition2: Object = {
    date: { year: this.curDate.getFullYear(), month: this.curDate.getMonth() + 1, day: this.curDate.getDate() }};
  train = new SearchValue( '广州', '' , '', this.condition, this.condition2);
  private isOpen = false;
  constructor(
    private modalService: NgbModal,
  ) {
    const emit = new SearchValue (
      this.train.city,
      this.train.trainName,
      this.train.trainStatus,
      this.train.dateFrom,
      this.train.dateTo,
    );
    this.search.emit(emit);
  }

  add(): NgbModalRef {
    if (this.isOpen) {
      return;
    }
    this.isOpen = true;
    const modalRef = this.modalService.open(TrainAddComponent, {
      container: 'sample-train',
    });
    modalRef.result.then((result) => {
      this.isOpen = false;
    }, (reason) => {
      this.isOpen = false;
    });
  }
  ngOnInit(): void {
  }

  searchTrainClick(e: MouseEvent) {
    const emit = new SearchValue (
      this.train.city,
      this.train.trainName,
      this.train.trainStatus,
      this.train.dateFrom,
      this.train.dateTo,
    );
    this.search.emit(emit);
  }

}
