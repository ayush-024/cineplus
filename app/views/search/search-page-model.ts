import { Observable, Frame } from '@nativescript/core';
import { TMDBService, SearchResult } from '../../services/tmdb-service';

export class SearchPageModel extends Observable {
  private tmdbService: TMDBService;
  private _isLoading: boolean = false;
  private _searchQuery: string = '';
  private _searchResults: SearchResult[] = [];
  private searchTimeout: number;

  constructor() {
    super();
    this.tmdbService = TMDBService.getInstance();
  }

  get isLoading(): boolean {
    return this._isLoading;
  }

  set isLoading(value: boolean) {
    if (this._isLoading !== value) {
      this._isLoading = value;
      this.notifyPropertyChange('isLoading', value);
    }
  }

  get searchQuery(): string {
    return this._searchQuery;
  }

  set searchQuery(value: string) {
    if (this._searchQuery !== value) {
      this._searchQuery = value;
      this.notifyPropertyChange('searchQuery', value);
      this.debounceSearch();
    }
  }

  get searchResults(): SearchResult[] {
    return this._searchResults;
  }

  set searchResults(value: SearchResult[]) {
    if (this._searchResults !== value) {
      this._searchResults = value.map(result => ({
        ...result,
        poster_path: this.tmdbService.getImageUrl(result.poster_path)
      }));
      this.notifyPropertyChange('searchResults', this._searchResults);
    }
  }

  private debounceSearch() {
    if (this.searchTimeout) {
      clearTimeout(this.searchTimeout);
    }
    
    this.searchTimeout = setTimeout(() => {
      this.performSearch();
    }, 500);
  }

  private async performSearch() {
    if (this.searchQuery.trim().length === 0) {
      this.searchResults = [];
      return;
    }

    try {
      this.isLoading = true;
      const results = await this.tmdbService.searchMulti(this.searchQuery);
      this.searchResults = results;
    } catch (error) {
      console.error('Error searching:', error);
      this.searchResults = [];
    } finally {
      this.isLoading = false;
    }
  }

  navigateToDetail(result: SearchResult) {
    const frame = Frame.topmost();
    frame.navigate({
      moduleName: 'views/detail/detail-page',
      context: { content: result, type: result.media_type }
    });
  }
}