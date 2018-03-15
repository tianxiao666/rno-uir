import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { TestSample } from './testSample';
import { environment } from '../../../../environments/environment';
import { Http } from '@angular/http';
import { JhiEventManager } from 'ng-jhipster';
@Component({
  selector: 'test-add',
  templateUrl: './test-add.component.html',
})

export class TestAddComponent implements OnInit {
  env = environment;
  sample = new TestSample('', '', '');
  resultInfo = '任务提交成功！测试任务开始';
  @ViewChild('successAddTrain') public successAddTrain: ElementRef;
  @ViewChild('programError') public programError: ElementRef;
  public isShowLoading: boolean = false;
  public sampleList: any;

  constructor(
    private activeModal: NgbActiveModal,
    private http: Http,
    private eventManager: JhiEventManager,
  ) {
    this.getTestSample().then(data => this.sampleList = data);
  }
  ngOnInit(): void {
  }
  getTestSample(): Promise<any> {
    return this.http.get(`${this.env.host}/api/searchTestSample`)
      .toPromise()
      .then(response => response.json())
      .catch(Promise.reject);
  }

  addTestSample() {
    const url = `${this.env.host}/api/addTestTask?sampleName=${this.sample.testName}`
      + `&sampleExplain=${this.sample.testExplain}&sampleResource=${this.sample.testResource}`;
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
          name: 'addTestTaskSuccess',
          content: 'Adding TestTask Success',
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
