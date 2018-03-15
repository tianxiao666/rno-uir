import { Injectable } from '@angular/core';
import { URLSearchParams, BaseRequestOptions, Http, Response } from '@angular/http';
import { TableSearchValue } from './table-search-value';
import { Observable } from 'rxjs/Observable';
import { ResponseWrapper } from '../common/response-wrapper';
import { environment } from '../../../environments/environment';

@Injectable()
export class DataSearchService {

  private env = environment;
  private resourceUrl;

  constructor(private http: Http) {
  }

  public getData(inputs: TableSearchValue): Promise<any> {
    if (inputs.operate === 'query') {
      this.resourceUrl  = '/api/_search/data-searches';
    }else if (inputs.operate === 'download') {
      this.resourceUrl  = '/api/_search/data-download';
    }
    // console.log(inputs);
    const res: Observable<ResponseWrapper> = this.query(inputs);
    return res.toPromise();
  }

  private query(req?: any): Observable<ResponseWrapper> {
    const url = `${this.env.host}/${this.resourceUrl}`;
    const options = this.createRequestOption(req);
    return this.http.get(url, options)
      .map((res: Response) => this.convertResponse(res));
  }

  private createRequestOption(req?: any): BaseRequestOptions {
    // console.log(req);
    const options: BaseRequestOptions = new BaseRequestOptions();
    if (req) {
      const params: URLSearchParams = new URLSearchParams();
      if (req.date1) {
        const dateModel1 = req.date1;
        const date1: Date = new Date(dateModel1.date.year, dateModel1.date.month - 1, dateModel1.date.day);
        const dateMilli1: number = date1.setHours(12);
        params.set('date1', dateMilli1.toString());
      }
      if (req.date2) {
        const dateModel2 = req.date2;
        const date2: Date = new Date(dateModel2.date.year, dateModel2.date.month - 1, dateModel2.date.day);
        const dateMilli2: number = date2.setHours(12);
        params.set('date2', dateMilli2.toString());
      }
      params.append('area', req.area);
      params.append('device', req.device);
      params.append('interfere_grade', req.assortment);
      params.append('cell', req.cellAreaInput.trim());
      params.append('cell_name', req.cellNameInput.trim());
      if (req.rowsOnPage) {
        if (req.rowsOnPage !== -1) {
          params.append('size', req.rowsOnPage);
        }
      }
      if (req.activePage) {
        if (req.activePage !== -1) {
          params.append('page', req.activePage);
        }
      }
      options.params = params;
    }
    return options;
  }

  private convertResponse(res: Response): ResponseWrapper {
    return new ResponseWrapper(res.headers, res.json(), res.status);
  }
}
