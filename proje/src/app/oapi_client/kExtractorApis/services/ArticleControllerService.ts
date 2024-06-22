/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import type { Observable } from 'rxjs';
import type { Article } from '../models/Article';
import type { DataResultListArticle } from '../models/DataResultListArticle';
import type { Result } from '../models/Result';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
@Injectable({
    providedIn: 'root',
})
export class ArticleControllerService {
    constructor(public readonly http: HttpClient) {}
    /**
     * @param id
     * @returns Result OK
     * @throws ApiError
     */
    public delete(
        id: number,
    ): Observable<Result> {
        return __request(OpenAPI, this.http, {
            method: 'POST',
            url: '/api/articles/delete/{id}',
            path: {
                'id': id,
            },
        });
    }
    /**
     * @param requestBody
     * @returns Result OK
     * @throws ApiError
     */
    public add(
        requestBody: Article,
    ): Observable<Result> {
        return __request(OpenAPI, this.http, {
            method: 'POST',
            url: '/api/articles/add',
            body: requestBody,
            mediaType: 'application/json',
        });
    }
    /**
     * @returns DataResultListArticle OK
     * @throws ApiError
     */
    public getAll(): Observable<DataResultListArticle> {
        return __request(OpenAPI, this.http, {
            method: 'GET',
            url: '/api/articles/getall',
        });
    }
}
