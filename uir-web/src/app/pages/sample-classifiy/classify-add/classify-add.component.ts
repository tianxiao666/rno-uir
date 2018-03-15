import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Classify } from './classify';
import { environment } from '../../../../environments/environment';
import { Http } from '@angular/http';
import { JhiEventManager } from 'ng-jhipster';
@Component({
  selector: 'classify-add',
  templateUrl: './classify-add.component.html',
})

export class ClassifyAddComponent implements OnInit {
  env = environment;
  classify = new Classify('', '', '');
  resultInfo = '任务提交成功！分类任务开始';
  @ViewChild('successAddTrain') public successAddTrain: ElementRef;
  @ViewChild('programError') public programError: ElementRef;
  public isShowLoading: boolean = false;
  public sampleList: any;
  constructor(
    private activeModal: NgbActiveModal,
    private http: Http,
    private eventManager: JhiEventManager,
  ) {
    this.getClassifySample().then( data => this.sampleList = data);
  }
  ngOnInit(): void {

  }
  getClassifySample(): Promise<any> {
    return this.http.get(`${this.env.host}/api/searchClassifySample`)
      .toPromise()
      .then(response => response.json())
      .catch(Promise.reject);
  }
  addClassifySample() {
    const url = `${this.env.host}/api/addClassifyTask?sampleName=${this.classify.classifyName}`
      + `&sampleExplain=${this.classify.classifyExplain}&sampleResource=${this.classify.classifyResource}`;
    this.isShowLoading = true;
    this.http.get(url).subscribe( data => {
      this.isShowLoading = false;
       this.resultInfo = data.text();
      setTimeout(() => {
        this.successAddTrain.nativeElement.style.display = 'block';
      }, 0);
      setTimeout(() => {
        this.successAddTrain.nativeElement.style.display = 'none';
        this.eventManager.broadcast({
          name: 'addClassifyTaskSuccess',
          content: 'Adding ClassifyTask Success',
        });
        this.activeModal.dismiss();
      }, 2000);

    }, error => {
      this.isShowLoading = false;
      setTimeout(() => {
        this.programError.nativeElement.style.display = 'block';
      }, 0);
      setTimeout(() => {
        this.programError.nativeElement.style.display = 'none';
        this.activeModal.dismiss();
      }, 2000);

      return;
    });
  }
  closeModal() {
    this.activeModal.dismiss();
  }
}

