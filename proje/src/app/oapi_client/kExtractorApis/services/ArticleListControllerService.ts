/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import type { Observable } from 'rxjs';
import type { Article } from '../models/Article';
import type { FilterArticleRequest } from '../models/FilterArticleRequest';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
@Injectable({
    providedIn: 'root',
})
export class ArticleListControllerService {
    constructor(public readonly http: HttpClient) {}
    /**
     * @param requestBody
     * @returns Article OK
     * @throws ApiError
     */
    public listAllArticles(
        requestBody: FilterArticleRequest,
    ): Observable<Array<Article>> {
        return __request(OpenAPI, this.http, {
            method: 'POST',
            url: '/api/articles/listAllArticles',
            body: requestBody,
            mediaType: 'application/json',
        });
    }
}
