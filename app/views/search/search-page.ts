import { EventData, Page } from '@nativescript/core';
import { SearchPageModel } from './search-page-model';

export function navigatingTo(args: EventData) {
  const page = <Page>args.object;
  page.bindingContext = new SearchPageModel();
}

export function onSearchTextChange(args: any) {
  const page = args.object.page;
  const model = page.bindingContext as SearchPageModel;
  model.searchQuery = args.object.text;
}

export function onSearchResultTap(args: any) {
  const page = args.object.page;
  const model = page.bindingContext as SearchPageModel;
  model.navigateToDetail(args.object.bindingContext);
}