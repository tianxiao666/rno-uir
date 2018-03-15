import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Sample } from './sample';
import { Http } from '@angular/http';
import { environment } from '../../../../environments/environment';
import { Object } from '../../common/Object';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'train-add',
  templateUrl: './train-add.component.html',
})

export class TrainAddComponent implements OnInit {

  @ViewChild('successAddTrain') public successAddTrain: ElementRef;
  @ViewChild('programError') public programError: ElementRef;
  public isShowLoading = false;
  env= environment;
  sample = new Sample('', '', '');
  sampleName: string;
  sampleExplain: string;
  sampleResource: string;
  resultInfo: string = '添加训练任务成功，训练开始';
  sampleList = new Array();
  private curDate = new Date();
  private condition: Object = {
    date: { year: this.curDate.getFullYear() - 1, month: this.curDate.getMonth() + 1, day: this.curDate.getDate() }};
  private condition2: Object = {
    date: { year: this.curDate.getFullYear(), month: this.curDate.getMonth() + 1, day: this.curDate.getDate() }};
  constructor(
    private activeModal: NgbActiveModal,
    private http: Http,
    private eventManager: JhiEventManager,
  ) {
    this.getTrainSample().then(data => this.sampleList = data);
  }

  ngOnInit(): void {
  }

  getTrainSample(): Promise<any> {
    return this.http.get(`${this.env.host}/api/searchTrainSample`)
      .toPromise()
      .then(response => response.json())
      .catch(Promise.reject);
  }

  addSampleForm(e: MouseEvent) {
    const url = `${this.env.host}/api/addTrainTask?sampleName=${this.sampleName}`
      + `&sampleExplain=${this.sampleExplain}&sampleResource=${this.sampleResource}`;
    this.isShowLoading = true;
    this.http.get(url).subscribe( res => {
      this.isShowLoading = false;
       this.resultInfo = res.text();

      setTimeout(() => {
        this.successAddTrain.nativeElement.style.display = 'block';
      }, 0);
      setTimeout(() => {
        this.successAddTrain.nativeElement.style.display = 'none';
        this.eventManager.broadcast({
          name: 'addTrainTaskSuccess',
          content: 'Adding TrainTask Success',
        });
        this.activeModal.dismiss('success');

      }, 2000);
    }, error => {
      this.isShowLoading = false;
      setTimeout(() => {
        this.programError.nativeElement.style.display = 'block';
      }, 0);
      setTimeout(() => {
        this.programError.nativeElement.style.display = 'none';
        this.activeModal.dismiss('error');
      }, 2000);

      return;
    });
  }
  closeModal() {
    this.activeModal.dismiss();
  }
}
