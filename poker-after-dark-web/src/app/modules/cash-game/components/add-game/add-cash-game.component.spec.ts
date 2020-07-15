import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { AddCashGameComponent } from './add-cash-game.component';

describe('AddGameComponent', () => {
  let component: AddCashGameComponent;
  let fixture: ComponentFixture<AddCashGameComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddCashGameComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCashGameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
