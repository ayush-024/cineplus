import { EventData, Page } from '@nativescript/core';
import { MoviesPageModel } from './movies-page-model';

export function navigatingTo(args: EventData) {
  const page = <Page>args.object;
  page.bindingContext = new MoviesPageModel();
}

export function onPopularTap(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as MoviesPageModel;
  model.loadMovies('popular');
}

export function onTopRatedTap(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as MoviesPageModel;
  model.loadMovies('top_rated');
}

export function onUpcomingTap(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as MoviesPageModel;
  model.loadMovies('upcoming');
}

export function onMovieItemTap(args: any) {
  const page = args.object.page;
  const model = page.bindingContext as MoviesPageModel;
  model.navigateToMovieDetail(args.object.bindingContext);
}